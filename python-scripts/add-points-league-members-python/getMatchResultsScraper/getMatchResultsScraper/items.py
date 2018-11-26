# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class GetmatchresultsscraperItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    pass

class MatchResultItem(scrapy.Item):
	date= scrapy.Field()
	teamName1 = scrapy.Field()
	teamName2 = scrapy.Field()
	teamGoal1 = scrapy.Field()
	teamGoal2 = scrapy.Field()
	scoreSummary = scrapy.Field()
	goalieSummary = scrapy.Field() 
